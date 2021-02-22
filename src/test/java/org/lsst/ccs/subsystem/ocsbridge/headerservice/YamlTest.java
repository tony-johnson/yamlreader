package org.lsst.ccs.subsystem.ocsbridge.headerservice;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;
import static junit.framework.Assert.assertNotNull;
import org.junit.Test;
import org.lsst.ccs.subsystem.ocsbrige.headerservice.Header;

/**
 *
 * @author tonyj
 */
public class YamlTest {

    @Test
    public void yamlTest() throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.findAndRegisterModules();
        try (InputStream in = YamlTest.class.getResourceAsStream("data.yaml")) {
            assertNotNull(in);
            Map<String, List<Header>> result = mapper.readValue(in, new TypeReference<Map<String, List<Header>>>() {});
            Set<String> keys = result.keySet();
            List<Header> headers = result.get("PRIMARY");
            for (Header header : headers) {
                System.out.println(header);
            }
        }
    }
}
