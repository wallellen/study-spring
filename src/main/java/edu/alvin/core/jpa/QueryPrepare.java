package edu.alvin.core.jpa;

import javax.persistence.Query;

public interface QueryPrepare {
    void prepare(Query query);
}
