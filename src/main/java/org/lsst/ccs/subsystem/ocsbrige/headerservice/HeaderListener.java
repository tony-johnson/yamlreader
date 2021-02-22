package org.lsst.ccs.subsystem.ocsbrige.headerservice;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import org.lsst.sal.SALEvent;
import org.lsst.sal.SALException;
import org.lsst.sal.atheader.SALMain;
import org.lsst.sal.atheader.event.LargeFileObjectAvailableEvent;

/**
 *
 * @author tonyj
 */
public class HeaderListener {

    private final SALMain mgr;
    private final ObjectMapper mapper;
    private final TypeReference<Map<String, List<Header>>> yamlType = new TypeReference<Map<String, List<Header>>>() {};
    
    public static void main(String[] args) throws SALException, IOException {
        HeaderListener hl = new HeaderListener();
        hl.run();
    }

    HeaderListener() {
        mapper = new ObjectMapper(new YAMLFactory());
        mapper.findAndRegisterModules();

        mgr = SALMain.create();
    }

    private void run() throws SALException, MalformedURLException, IOException {
        for (;;) {
            SALEvent event = mgr.getNextEvent(Duration.ofMinutes(1));
            if (event instanceof LargeFileObjectAvailableEvent) {
                LargeFileObjectAvailableEvent lfoEvent = (LargeFileObjectAvailableEvent) event;
                String url = lfoEvent.getUrl();
                System.out.println("Got " + url);
                processEvent(url);
            }
        }
    }
    
    private void processEvent(String url) throws MalformedURLException, IOException {
         Map<String, List<Header>>  header = mapper.readValue(new URL(url), yamlType);
         System.out.println("Got " + header.get("PRIMARY"));
    }


}
