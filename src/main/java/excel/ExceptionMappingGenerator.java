package excel;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ExceptionMappingGenerator {
    // 需要转换的模块 sheet name
    private static final String[] MODULES = new String[]{
            "ykc",
            "loc",
            "lmc",
            "lsc",
    };
    // 异常码开始读取所在行
    private static final int START_ROW_NUM = 2;
    // 原始异常码开始读取所在列
    private static final int ORIGINAL_EXCEPTION_CODE_POSITION = 1;
    // 原始异常信息开始读取所在列
    private static final int ORIGINAL_EXCEPTION_MESSAGE_POSITION = 2;
    // 转换异常信息开始读取所在列
    private static final int TRANSFORMED_EXCEPTION_MESSAGE_POSITION = 4;
    // 源文件路径
    private static final String FILE_PATH = "/Users/unknown7/Documents/exception_mapping.xlsx";

    private static final Map<String, Integer> SHEETS = new HashMap<String, Integer>() {{
        for (String module : MODULES) {
            put(module, 0);
        }
    }};
    private static final Multimap<String, String> CODE_CONVERTED = ArrayListMultimap.create();
    private static final String TIME = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

    public static void main(String[] args) throws IOException, InvalidFormatException {
        Workbook workbook = ExcelUtil.getWorkbook(FILE_PATH);
        int numberOfSheets = workbook.getNumberOfSheets();
        for (int i = 0; i < numberOfSheets; i++) {
            Sheet sheet = workbook.getSheetAt(i);
            if (sheet == null || SHEETS.get(sheet.getSheetName()) == null) {
                continue;
            }
            int rowNos = sheet.getLastRowNum();// 得到excel的总记录条数
            for (int j = START_ROW_NUM; j <= rowNos; j++) {// 遍历行
                Row row = sheet.getRow(j);
                if (row != null) {
                    String originalCode = row.getCell(ORIGINAL_EXCEPTION_CODE_POSITION).getStringCellValue();
                    String originalMessage = row.getCell(ORIGINAL_EXCEPTION_MESSAGE_POSITION).getStringCellValue();
                    String transformedMessage = row.getCell(TRANSFORMED_EXCEPTION_MESSAGE_POSITION).getStringCellValue();
                    if (StringUtils.isNotEmpty(transformedMessage)) {
                        final String EXCEPTION = sheet.getSheetName().substring(0, 1).toUpperCase() + sheet.getSheetName().substring(1) + "BusinessException";
                        final String ORIGINAL_CODE = originalCode;
                        final String ORIGINAL_MESSAGE = originalMessage;
                        final String TRANSFORMED_DEFAULT_CODE = null;
                        final String TRANSFORMED_DEFAULT_MESSAGE = StringUtils.isBlank(transformedMessage) ? null : transformedMessage;
                        final String MODULE = sheet.getSheetName();
                        final Integer DYNAMIC_BINDING_SWITCH = 1;
                        final String DYNAMIC_BINDING = "[]";
                        final Integer HIDDEN = 0;
                        final String CREATE_TIME = TIME;
                        final String UPDATE_TIME = TIME;
                        final Integer DEL_FLAG = 0;

                        StringBuilder builder = new StringBuilder("INSERT INTO db_gmcf_gwc.t_gwc_exception_mapping (exception, original_code, original_message, transformed_default_code, transformed_default_message, module, dynamic_binding_switch, dynamic_binding, hidden, create_time, update_time, del_flag) VALUES (");
                        builder.append("'" + EXCEPTION + "'")
                                .append(", ")
                                .append("'" + ORIGINAL_CODE + "'")
                                .append(", ")
                                .append("'" + ORIGINAL_MESSAGE + "'")
                                .append(", ")
                                .append(TRANSFORMED_DEFAULT_CODE)
                                .append(", ")
                                .append("'" + TRANSFORMED_DEFAULT_MESSAGE + "'")
                                .append(", ")
                                .append("'" + MODULE + "'")
                                .append(", ")
                                .append(DYNAMIC_BINDING_SWITCH)
                                .append(", ")
                                .append("'" + DYNAMIC_BINDING + "'")
                                .append(", ")
                                .append(HIDDEN)
                                .append(", ")
                                .append("'" + CREATE_TIME + "'")
                                .append(", ")
                                .append("'" + UPDATE_TIME + "'")
                                .append(", ")
                                .append(DEL_FLAG)
                                .append(");");

                        System.err.println(builder);
                        Integer curNum = SHEETS.get(MODULE);
                        SHEETS.put(MODULE, ++curNum);
                        CODE_CONVERTED.put(ORIGINAL_CODE, MODULE);
                    }
                }
            }
        }

        System.err.println();
        System.err.println("[-----------------repetitive code------------------]");
        Map<String, Collection<String>> converted = CODE_CONVERTED.asMap();
        for (Map.Entry<String, Collection<String>> entry : converted.entrySet()) {
            if (entry.getValue().size() > 1) {
                System.err.println(entry.getKey() + ": " + entry.getValue());
            }
        }

        System.err.println();
        System.err.println("[---------------converted statistics---------------]");
        int total = 0;
        for (Map.Entry<String, Integer> entry : SHEETS.entrySet()) {
            Integer num = entry.getValue();
            System.err.println(entry.getKey() + ": " + num);
            total += num;
        }
        System.err.println("total: " + total);
    }
}
