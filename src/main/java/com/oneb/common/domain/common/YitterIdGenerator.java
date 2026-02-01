package com.oneb.common.domain.common;

import com.github.yitter.idgen.YitIdHelper;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class YitterIdGenerator implements IdentifierGenerator {

    @Override
    public Object generate(SharedSessionContractImplementor session, Object object) {
        return YitIdHelper.nextId();
    }
}