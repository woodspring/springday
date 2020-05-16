package converter;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;
public class ZonedDateTimeReadConverter implements Converter<Date, ZonedDateTime> {

	@Override
	public ZonedDateTime convert(Date source) {
		return source.toInstant().atZone(ZoneOffset.UTC);
	}

}
