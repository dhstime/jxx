package com.jxx.solr;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 功能描述
 *
 * @author strange
 * @date $
 */
@Service
public class BooksServiceImpl implements BooksService{

    @Override
    public List<Books> searchByName(String name) {
        return null;
    }

    @Override
    public List<Books> searchWithPageable(Integer pageNum, Integer pageSize) {
        return null;
    }

    @Override
    public List<Books> searchWithHighlight(Integer pageNum, Integer pageSize) {
        return null;
    }
}
