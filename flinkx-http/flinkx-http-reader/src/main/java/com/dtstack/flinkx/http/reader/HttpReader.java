package com.dtstack.flinkx.http.reader;

import com.dtstack.flinkx.config.DataTransferConfig;
import com.dtstack.flinkx.config.ReaderConfig;
import com.dtstack.flinkx.http.HttpConfigConstants;
import com.dtstack.flinkx.http.HttpConfigKeys;
import com.dtstack.flinkx.reader.DataReader;
import com.dtstack.flinkx.reader.MetaColumn;
import com.dtstack.flinkx.util.StringUtil;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.types.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * HttpReader
 *
 * @author GeZhiHui
 * @create 2018-07-25
 **/

public class HttpReader extends DataReader {

    private String httpUrl;
    private String fieldDelimiter;
    private String encoding;

//    private List<Integer> columnIndex;
//    private List<String> columnType;
//    private List<String> columnValue;
    private List<MetaColumn> metaColumns;


    public HttpReader(DataTransferConfig config, StreamExecutionEnvironment env) {
        super(config, env);
        ReaderConfig readerConfig = config.getJob().getContent().get(0).getReader();
        this.httpUrl = readerConfig.getParameter().getStringVal(HttpConfigKeys.KEY_HTTP_URL);
        this.fieldDelimiter = readerConfig.getParameter().getStringVal(HttpConfigKeys.KEY_FIELD_DELIMITER, HttpConfigConstants.DEFAULT_FIELD_DELIMITER);
        this.encoding = readerConfig.getParameter().getStringVal(HttpConfigKeys.KEY_ENCODING);
        if(!this.fieldDelimiter.equals(HttpConfigConstants.DEFAULT_FIELD_DELIMITER)) {
            this.fieldDelimiter = StringUtil.convertRegularExpr(fieldDelimiter);
        }

        List columns = readerConfig.getParameter().getColumn();
        metaColumns = MetaColumn.getMetaColumns(columns);
//        if(columns != null && !columns.isEmpty()) {
//            if (columns.get(0) instanceof Map) {
//                columnIndex = new ArrayList();
//                columnType = new ArrayList<>();
//                columnValue = new ArrayList<>();
//                for (int i = 0; i < columns.size(); ++i) {
//                    Map sm = (Map) columns.get(i);
//                    Double temp = (Double) sm.get("index");
//                    columnIndex.add(temp != null ? temp.intValue() : null);
//                    columnType.add((String) sm.get("type"));
//                    columnValue.add((String) sm.get("value"));
//                }
//            } else if (!columns.get(0).equals("*") || columns.size() != 1) {
//                throw new IllegalArgumentException("column argument error");
//            }
//        }

    }

    @Override
    public DataStream<Row> readData() {

        HttpInputFormatBuilder builder = new HttpInputFormatBuilder();
        builder.setMetaColumn(metaColumns);
//        builder.setColumnIndex(this.columnIndex);
//        builder.setColumnType(this.columnType);
//        builder.setColumnValue(this.columnValue);
        builder.setDelimiter(this.fieldDelimiter);
        builder.setHttpUrl(this.httpUrl);
        builder.setEncoding(this.encoding);

        return createInput(builder.finish(), "httpreader");
    }
}
