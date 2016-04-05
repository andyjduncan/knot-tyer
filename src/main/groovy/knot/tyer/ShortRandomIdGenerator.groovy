package knot.tyer

import org.apache.commons.lang.RandomStringUtils
import org.hibernate.HibernateException
import org.hibernate.engine.spi.SessionImplementor
import org.hibernate.id.IdentifierGenerator

class ShortRandomIdGenerator implements IdentifierGenerator {

    @Override
    Serializable generate(SessionImplementor session, Object object) throws HibernateException {
        RandomStringUtils.random(10, true, true)
    }
}
