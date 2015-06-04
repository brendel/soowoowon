package edufarm.service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import net.sf.uadetector.ReadableUserAgent;
import net.sf.uadetector.UserAgentStringParser;
import net.sf.uadetector.service.UADetectorServiceFactory;

import java.util.concurrent.TimeUnit;

/**
 * User-Agent Parser.
 * <p>한 번 읽은 user agent 문자열을 캐싱한다.
 *
 * @author Kiwon Lee <bwv1050@gmail.com>
 */
public class CachedUserAgentStringParser implements UserAgentStringParser {
    private final UserAgentStringParser parser = UADetectorServiceFactory
            .getCachingAndUpdatingParser();
    private final Cache<String, ReadableUserAgent> cache = CacheBuilder.newBuilder()
            .maximumSize(100)
            .expireAfterWrite(2, TimeUnit.HOURS)
            .build();

    @Override
    public String getDataVersion() {
        return parser.getDataVersion();
    }

    @Override
    public ReadableUserAgent parse(final String userAgentString) {
        ReadableUserAgent result = cache.getIfPresent(userAgentString);
        if (result == null) {
            result = parser.parse(userAgentString);
            cache.put(userAgentString, result);
        }
        return result;
    }

    @Override
    public void shutdown() {
        parser.shutdown();
    }
}
