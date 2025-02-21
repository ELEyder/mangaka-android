package com.senati.mangaka.data;

public interface ApiManga {
    void getMangaList(final VolleyCallback callback);
    void getCaps(final VolleyCallback callback);
    void getCaps(final String id, final VolleyCallback callback);
    void getCap(final VolleyCallback callback);
    void getCap(final String capId, final VolleyCallback callback);
}
