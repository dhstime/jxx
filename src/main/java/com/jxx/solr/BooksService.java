package com.jxx.solr;

import java.util.List;

/**
 * 功能描述
 *
 * @author strange
 * @date $
 */
public interface BooksService {
    public List<Books> searchByName(String name);

    public List<Books> searchWithPageable(Integer pageNum,Integer pageSize);

    public List<Books> searchWithHighlight(Integer pageNum,Integer pageSize);
}
