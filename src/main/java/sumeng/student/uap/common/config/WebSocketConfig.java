/**
 * Copyright (C), 2019-2020, sumeng
 * FileName: WebSocketConfig
 * Author: sumeng
 * Date: 2020/4/2 23:55
 */
package sumeng.student.uap.common.config;/**
 * @ClassName: WebSocketConfig
 * @Description: 111
 * @Author: sumeng
 * @Version: 1.0.0
 * @Date: 2020/4/2 23:55
 */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 *
 *
 * @date 2020/4/2  23:55
 * @author 12068
 */
@Configuration
public class WebSocketConfig {
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
