package delivery.model.dao;

public abstract class DaoFactoryAbst {
    private static DaoFactoryAbst daoFactoryAbst;

    public abstract UserDao createUserDao();

    public static DaoFactoryAbst getInstance(){
        if( daoFactoryAbst == null ){
            synchronized (DaoFactoryAbst.class){
                if(daoFactoryAbst ==null){
                    daoFactoryAbst = new FactoryDaoAbst();
                }
            }
        }
        return daoFactoryAbst;
    }
}
