package fr.cda.centaleish.service.interfaces;

public interface ServiceInterface<T, L, C, U> {

    T create(C o);

    T update(U o, L id);

    void delete(L id);

    T findOneById(L id);

}
