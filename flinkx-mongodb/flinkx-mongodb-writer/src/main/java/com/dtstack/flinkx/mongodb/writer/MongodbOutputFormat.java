/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dtstack.flinkx.mongodb.writer;

import com.dtstack.flinkx.exception.WriteRecordException;
import com.dtstack.flinkx.mongodb.MongodbUtil;
import com.dtstack.flinkx.outputformat.RichOutputFormat;
import com.dtstack.flinkx.reader.MetaColumn;
import com.dtstack.flinkx.writer.WriteMode;
import com.mongodb.client.MongoCollection;
import org.apache.commons.lang.StringUtils;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.types.Row;
import org.bson.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.dtstack.flinkx.mongodb.MongodbConfigKeys.*;

/**
 * OutputFormat for mongodb writer plugin
 *
 * @Company: www.dtstack.com
 * @author jiangbo
 */
public class MongodbOutputFormat extends RichOutputFormat {

    protected String hostPorts;

    protected String username;

    protected String password;

    protected String database;

    protected String collectionName;

    protected List<MetaColumn> columns;

    protected String replaceKey;

    protected String mode = WriteMode.INSERT.getMode();

    private MongoCollection<Document> collection;

    @Override
    public void configure(Configuration parameters) {
        super.configure(parameters);

        Map<String,String> config = new HashMap<>(4);
        config.put(KEY_HOST_PORTS,hostPorts);
        config.put(KEY_USERNAME,username);
        config.put(KEY_PASSWORD,password);
        config.put(KEY_DATABASE,database);

        collection = MongodbUtil.getCollection(config,database,collectionName);
    }

    @Override
    protected void openInternal(int taskNumber, int numTasks) throws IOException {

    }

    @Override
    protected void writeSingleRecordInternal(Row row) throws WriteRecordException {
        Document doc = MongodbUtil.convertRowToDoc(row,columns);

        if(WriteMode.INSERT.getMode().equals(mode)){
            collection.insertOne(doc);
        } else if(WriteMode.REPLACE.getMode().equals(mode) || WriteMode.UPDATE.getMode().equals(mode)){
            if(StringUtils.isEmpty(replaceKey)){
                throw new IllegalArgumentException("ReplaceKey cannot be empty when the write mode is replace");
            }

            if(!doc.containsKey(replaceKey)){
                throw new IllegalArgumentException("Cannot find replaceKey in the input fields");
            }

            Document filter = new Document(replaceKey,doc.get(replaceKey));
            collection.findOneAndReplace(filter,doc);
        }
    }

    @Override
    protected void writeMultipleRecordsInternal() throws Exception {
        List<Document> documents = new ArrayList<>(rows.size());
        for (Row row : rows) {
            documents.add(MongodbUtil.convertRowToDoc(row,columns));
        }

        if(WriteMode.INSERT.getMode().equals(mode)){
            collection.insertMany(documents);
        } else if(WriteMode.UPDATE.getMode().equals(mode)) {
            throw new RuntimeException("Does not support batch update documents");
        } else if(WriteMode.REPLACE.getMode().equals(mode)){
            throw new RuntimeException("Does not support batch replace documents");
        }
    }

    @Override
    public void closeInternal() throws IOException {
        super.closeInternal();
        MongodbUtil.close();
    }
}
