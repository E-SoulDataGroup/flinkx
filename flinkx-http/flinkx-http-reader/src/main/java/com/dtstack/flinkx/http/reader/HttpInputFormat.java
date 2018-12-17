package com.dtstack.flinkx.http.reader;

import com.dtstack.flinkx.inputformat.RichInputFormat;
import com.dtstack.flinkx.reader.MetaColumn;
import com.dtstack.flinkx.util.StringUtil;
import org.apache.flink.api.common.io.DefaultInputSplitAssigner;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.core.io.GenericInputSplit;
import org.apache.flink.core.io.InputSplit;
import org.apache.flink.core.io.InputSplitAssigner;
import org.apache.flink.types.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

/**
 * HttpInputFormat
 *
 * @author GeZhiHui
 * @create 2018-07-25
 **/

public class HttpInputFormat extends RichInputFormat {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpInputFormat.class);

    private static final long serialVersionUID = 4140405535499685688L;
    private transient BufferedReader br;
    private transient String line;

    protected String httpUrl;
    protected String delimiter = ",";
    protected String encoding = "UTF-8";
    protected String startMark = "*";

    protected List<MetaColumn> metaColumns;
    protected String httpFileName;
    protected Long rowNum;
    protected String fileNameRowNum;

    @Override
    protected void openInternal(InputSplit inputSplit) throws IOException {
        URL url = new URL(httpUrl);
        URLConnection urlc = url.openConnection();
        HttpURLConnection httpUrlConnection = (HttpURLConnection) urlc;
        InputStream in = httpUrlConnection.getInputStream();
        br = new BufferedReader(new InputStreamReader(in, this.encoding));
    }

    @Override
    protected Row nextRecordInternal(Row row) {
        rowNum = super.numReadCounter.getLocalValue();
        fileNameRowNum = httpFileName + "_" + rowNum;
        String[] fields = (line + "," + fileNameRowNum).split(delimiter);
        if (metaColumns.size() == 1 && startMark.equals(metaColumns.get(0).getName())) {
            row = new Row(fields.length);
            for (int i = 0; i < fields.length; i++) {
                row.setField(i, fields[i]);
            }
        } else {
            row = new Row(metaColumns.size());
            for (int i = 0; i < metaColumns.size(); i++) {
                MetaColumn metaColumn = metaColumns.get(i);
                Object value;
                if (metaColumn.getValue() != null) {
                    value = metaColumn.getValue();
                } else if (metaColumn.getIndex() != null) {
                    value = fields[metaColumn.getIndex()];
                } else {
                    value = null;
                    {
                    }
                }
                if (value != null) {
                    value = StringUtil.string2col(String.valueOf(value), metaColumn.getType(), metaColumn.getTimeFormat());
                }
                row.setField(i, value);
            }
        }
        return row;
    }

    @Override
    protected void closeInternal() throws IOException {
        if (br != null) {
            br.close();
        }
    }

    @Override
    public void configure(Configuration parameters) {
    }

    @Override
    public InputSplit[] createInputSplits(int minNumSplits) {
        return new GenericInputSplit[]{new GenericInputSplit(0, 1)};
    }

    @Override
    public boolean reachedEnd() throws IOException {
        line = br.readLine();
        return line == null;
    }

    @Override
    public InputSplitAssigner getInputSplitAssigner(InputSplit[] inputSplits) {
        return new DefaultInputSplitAssigner(inputSplits);
    }
}
