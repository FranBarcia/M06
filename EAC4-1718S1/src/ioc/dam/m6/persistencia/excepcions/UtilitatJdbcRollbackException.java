/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ioc.dam.m6.persistencia.excepcions;

import java.sql.SQLException;

/**
 *
 * @author josep
 */
public class UtilitatJdbcRollbackException extends UtilitatJdbcSQLException {
    private SQLException motiuDelRollback=null;

    /**
     * Creates a new instance of <code>UtilitatJdbcRollbackException</code> without detail message.
     */
    public UtilitatJdbcRollbackException(SQLException cause, 
                                    SQLException motiuDelRollback) {
        super(cause);
        this.motiuDelRollback=motiuDelRollback;
    }

    /**
     * Constructs an instance of <code>UtilitatJdbcRollbackException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public UtilitatJdbcRollbackException(String msg, SQLException cause, 
                                    SQLException motiuDelRollback) {
        super(msg, cause);
        this.motiuDelRollback=motiuDelRollback;
    }

    /**
     * @return the motiuDelRollback
     */
    public SQLException getMotiuDelRollback() {
        return motiuDelRollback;
    }
}
