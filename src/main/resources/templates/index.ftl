<#-- @ftlvariable name="res" type="com.liyametrics.domain.elasticsearch.ShortArticle"-->
<#-- @ftlvariable name="queryResult" type="java.util.List"-->
<html>
  <head>
    <title>Liya Metrics</title>
  </head>
  <body>
  <a href="/"><h3>Liya Metrics</h3></a>

    <form action="/search" method="post">
        <input type="text" name="query" style="width: 300px">
        <button type="submit">Search!</button>
    </form>
    <#if queryResult??>
    <hr>
    <div class="articles">
        <#list queryResult as res>
            <div class="article">
                <h4 class="a_title">${res.title}</h4>
                <p class="a_rank">Rank : ${res.rank}</p>
                <p>Authors :
                    <#list res.authors as author>
                        <a class="a_author" href="/filterAuthor?author=${author}">${author}</a>
                    </#list>
                </p>
                <p class="a_text">${res.shortText}</p>
                <p class="categories">
                    <#list res.categories as category>
                        <a class="a_category" href="/filter?category=${category}">${category}</a>
                    </#list>
                </p>
            </div>
        </#list>
    </div>
    </#if>
    <hr>
  </body>
</html>
