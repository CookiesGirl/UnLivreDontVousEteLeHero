package application.model.DAO;

public abstract class DAO<T> {

    public abstract T findById(int id);


    public abstract T create(T obj);


    public abstract T update(T obj);


    public abstract void delete(T obj);

}
