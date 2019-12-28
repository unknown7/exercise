package algorithm.algorithms;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONToken;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.parser.deserializer.FieldDeserializer;
import com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.util.FieldInfo;
import com.alibaba.fastjson.util.JavaBeanInfo;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Map;

public class EncryptParserConfig extends ParserConfig {

    @Override
    public FieldDeserializer createFieldDeserializer(ParserConfig mapping, JavaBeanInfo beanInfo, FieldInfo fieldInfo) {
        FieldDeserializer deserializer;
        if (fieldInfo.field.isAnnotationPresent(Encrypt.class)) {
            deserializer = new FieldDeserializer(beanInfo.clazz, fieldInfo) {
                @Override
                public void parseField(DefaultJSONParser defaultJSONParser, Object o, Type type, Map<String, Object> map) {
                    System.err.println("parse");
                }

                @Override
                public void setValue(Object object, Object value) {
                    Method method = this.fieldInfo.method;
                    try {
                        method.invoke(object, value.toString() + "hahahahahahahah");
                    } catch (Exception e) {

                    }
                }

                @Override
                public int getFastMatchToken() {
                    return JSONToken.LITERAL_STRING;
                }
            };
        } else {
            deserializer = super.createFieldDeserializer(mapping, beanInfo, fieldInfo);
        }
        return deserializer;
    }

    @Override
    public ObjectDeserializer createJavaBeanDeserializer(Class<?> clazz, Type type) {
        return new JavaBeanDeserializer(this, clazz, type);
    }

    public static ParserConfig global;

    static {
        global = new EncryptParserConfig();
    }
}
