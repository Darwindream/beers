package ferran.com.brewdog.util;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 *
 * Created by Ferran Peña on 12-Jan-19.
 */
@Documented
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomScope {
}