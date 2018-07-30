package com.dtstack.flinkx.http.reader;

import com.dtstack.flinkx.inputformat.RichInputFormat;
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

    protected String httpUrl;
    protected String delimiter = ",";
    protected String encoding = "UTF-8";

    protected List<Integer> columnIndex;
    protected List<String> columnType;
    protected List<String> columnValue;

    private transient BufferedReader br;

    private transient String line;

    @Override
    protected void openInternal(InputSplit inputSplit) throws IOException {
        URL url = new URL(httpUrl);
        URLConnection urlc = url.openConnection();
        HttpURLConnection httpUrlConnection = (HttpURLConnection) urlc;
        InputStream in = httpUrlConnection.getInputStream();
        br = new BufferedReader(new InputStreamReader(in, this.encoding));
    }

    @Override
    protected Row nextRecordInternal(Row row) throws IOException {
        row = new Row(columnIndex.size());
        String[] fields = line.split(delimiter, -1);
        for (int i = 0; i < columnIndex.size(); ++i) {
            Integer index = columnIndex.get(i);
            String val = columnValue.get(i);
            if (index != null) {
                String col = fields[index];
                row.setField(i, col);
            } else if (val != null) {
                String type = columnType.get(i);
                Object col = StringUtil.string2col(val, type);
                row.setField(i, col);
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
    public InputSplit[] createInputSplits(int minNumSplits) throws IOException {
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
