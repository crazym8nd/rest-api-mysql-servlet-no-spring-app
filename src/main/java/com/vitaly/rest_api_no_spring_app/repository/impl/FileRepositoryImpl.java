package com.vitaly.rest_api_no_spring_app.repository.impl;
//  20-Jan-24
// gh crazym8nd


import com.vitaly.rest_api_no_spring_app.model.File;
import com.vitaly.rest_api_no_spring_app.model.Status;
import com.vitaly.rest_api_no_spring_app.repository.FileRepository;
import com.vitaly.rest_api_no_spring_app.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileRepositoryImpl implements FileRepository {

    @Override
    public List<File> getAll() {
        List<File> filesToShow = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            filesToShow = session.createQuery("FROM File WHERE status = :status", File.class)
                    .setParameter("status", Status.ACTIVE)
                    .list();
            return filesToShow;
        } catch (HibernateException e) {
            return Collections.emptyList();
        }
    }

    @Override
    public File getById(Integer integer) {
        File file = new File();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            file = (File) session.createQuery("FROM File WHERE id = :id")
                    .setParameter("id", integer)
                    .list().get(0);
        } catch (HibernateException e) {
            e.printStackTrace();
            System.out.println("Error while getting file by id ");
        }
        return file;
    }

    @Override
    public File create(File file) {
        if (file != null) {
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                session.beginTransaction();
                session.persist(file);
                session.getTransaction().commit();
            } catch (HibernateException e) {
                e.printStackTrace();
                System.out.println("Error while creating file ");
            }
        }
        return file;
    }

    @Override
    public File update(File file) {
        if (file != null) {
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                session.beginTransaction();
                session.merge(file);
                session.getTransaction().commit();
                return file;

            } catch (HibernateException e) {
                e.printStackTrace();
                System.out.println("Error while updating file ");
            }
        }
        return new File(-1, "NO SUCH FILE","",null);
    }

    @Override
    public void deleteById(Integer integer) {
        File file = getById(integer);
        if (file != null) {
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                session.beginTransaction();
                file.setStatus(Status.DELETED);
                session.merge(file);
                session.getTransaction().commit();
            } catch (HibernateException e) {
                e.printStackTrace();
                System.out.println("Error while deleting file ");
            }
        }
    }
}
