package com.traikers.appnoticia.models;

import java.util.List;



@SuppressWarnings("all")
public class Noticia {
    private final String status;

    private final int totalResults;

    private final List<Articles> articles;

    public Noticia(String status, int totalResults, List<Articles> articles) {
        this.status = status;
        this.totalResults = totalResults;
        this.articles = articles;
    }

    public String getStatus() {
        return status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public List<Articles> getArticles() {
        return articles;
    }

    public static class Articles {
        private final Source source;

        private final String author;

        private final String title;

        private final String description;

        private final String url;

        private final String urlToImage;

        private final String publishedAt;

        private final String content;

        public Articles(Source source, String author, String title, String description, String url,
                        String urlToImage, String publishedAt, String content) {
            this.source = source;
            this.author = author;
            this.title = title;
            this.description = description;
            this.url = url;
            this.urlToImage = urlToImage;
            this.publishedAt = publishedAt;
            this.content = content;
        }

        public Source getSource() {
            return source;
        }

        public String getAuthor() {
            return author;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        public String getUrl() {
            return url;
        }

        public String getUrlToImage() {
            return urlToImage;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public String getContent() {
            return content;
        }

        public static class Source {
            private final Object id;

            private final String name;

            public Source(Object id, String name) {
                this.id = id;
                this.name = name;
            }

            public Object getId() {
                return id;
            }

            public String getName() {
                return name;
            }
        }
    }
}
