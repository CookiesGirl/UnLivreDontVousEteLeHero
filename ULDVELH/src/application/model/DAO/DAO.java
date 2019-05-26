package application.model.DAO;

public abstract class DAO<T> {

    public abstract T findById(int id);


    public abstract void create(T obj);


    public abstract void update(T obj);


    public abstract void delete(T obj);

}
