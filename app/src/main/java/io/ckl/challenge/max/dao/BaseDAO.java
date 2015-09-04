package io.ckl.challenge.max.dao;

import com.raizlabs.android.dbflow.runtime.TransactionManager;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.Collection;

/**
 *
 * Base data access class with generic methods.
 *
 * Created by Max Jr on 04/09/2015.
 */
public abstract class BaseDAO<T extends BaseModel> {

    public void saveModelList(final Collection<T> models) {
        TransactionManager.transact(ChallengeDB.NAME, new Runnable() {
            @Override
            public void run() {
                for (T model : models) {
                    model.save();
                }
            }
        });
    }

}
