package demo.anno;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
 
import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**�Զ���ע�ⶨ�壬�˴��Զ���һ��ע�⣬���ֽ���JDBCConfig
 * @Target({METHOD,TYPE}) ��ʾ���ע�������������/�ӿ��ϣ����������ڷ�����
 * @Retention(RetentionPolicy.RUNTIME) ��ʾ����һ������ʱע�⣬����������֮�󣬲Ż�ȡע���е������Ϣ�����������ע����@Override ���ֲ������У��ڱ���ʱeclipse�Ϳ��Խ�����ع����ı���ʱע�⡣
 * @Inherited ��ʾ���ע����Ա�����̳�
 * @Documented ��ʾ��ִ��javadoc��ʱ�򣬱�ע�����������ĵ�
 * @author suxin
 *
 */
@Target({METHOD,TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface JDBCConfig {
	String ip();
    int port() default 3306;//Ĭ�϶˿ں�Ϊ3306
    String database();
    String encoding();
    String loginName();
    String password();
}
