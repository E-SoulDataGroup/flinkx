package com.dtstack.flinkx.http.reader;

import com.dtstack.flinkx.inputformat.RichInputFormatBuilder;
import com.dtstack.flinkx.reader.MetaColumn;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * HttpInputFormatBuilder
 *
 * @author GeZhiHui
 * @create 2018-07-25
 **/

public class HttpInputFormatBuilder extends RichInputFormatBuilder {

    private HttpInputFormat httpInputFormat;

    HttpInputFormatBuilder() {
        super.format = this.httpInputFormat = new HttpInputFormat();
    }

    public void setHttpUrl(String httpUrl) {
        httpInputFormat.httpUrl = httpUrl;
    }

    public void setDelimiter(String delimiter) {
        if (StringUtils.isNotEmpty(delimiter)) {
            httpInputFormat.delimiter = delimiter;
        }
    }

    public void setEncoding(String encoding) {
        if (StringUtils.isNotEmpty(encoding)) {
            httpInputFormat.encoding = encoding;
        }
    }

//    public void setColumnIndex(List<Integer> columnIndex) {
//        httpInputFormat.columnIndex = columnIndex;
//    }
//
//    public void setColumnValue(List<String> columnValue) {
//        httpInputFormat.columnValue = columnValue;
//    }
//
//    public void setColumnType(List<String> columnType) {
//        httpInputFormat.columnType = columnType;
//    }

    public void setMetaColumn(List<MetaColumn> metaColumns) {
        httpInputFormat.metaColumns = metaColumns;
    }

    @Override
    protected void checkFormat() {

    }
}
