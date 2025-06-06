# Questly 🧠
*A community-powered Q&A platform – ask, answer, learn.*

use these commands to run the docker instance of elastic search

```
docker pull elasticsearch:9.0.2
```

```
docker run -d --name elasticsearch-container \
-p 9200:9200 -p 9300:9300 \
-e "discovery.type=single-node" \
-e "xpack.security.enabled=false" \
-e "xpack.security.http.ssl.enabled=false" \
elasticsearch:9.0.2
```





