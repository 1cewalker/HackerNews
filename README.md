# Description

Hacker News by YCombinator is one of the most popular sources of tech news for developers. The site has been around for a while, but there are
no equivalent official Android clients. A lot of 3rd party developers attempt to create Android clients for Hacker News reader, by scraping site
content.
Recently, Hacker News released their initial version of API, allowing mobile developers for the first time a way to retrieve content through REST
API without having to timidly scrape content from its website.
The candidate is required to utilize this API to develop a simple Hacker News reader app. The app should contain 2 screens:
Home screen with list of most popular news (from topstories endpoint). For each item: display title, author handle, points and time from
present, and a button to open URL in external browser. The screen should have a 'pull to refresh' feature to allow users to manually
refresh the list. Refer to https://news.ycombinator.com/ for example.
Details screen (accessible by clicking an item on home screen): display latest 10 comments (content, author handle, time), for each
comment display latest reply (content, author handle, title) if any. Refer to https://news.ycombinator.com/item?id=8422599 for example.


# Requirements:
The app should have the latest Material Design look and feel, and needs to support devices from API 9 to 21.
Gradle should be used as the build system
jCenter or Maven Central should be used for resolving dependencies, no .jar dependencies are accepted. Use of 3rd-party libraries are
accepted, but needs to be specified as Maven/jCenter dependencies.
Use of Firebase client libraries (the one that hosts Hacker News API) is discouraged. Treat it as if only the REST API is available for use.
