On local instance JCR root node has "sling:redirect" resource type. 
This module do not override sling redirection behavior so to enable application 
root redirection please remove sling:resourceType property from "/" node.

# Usage:

Build
```
mvn clean install
```

Build with local deployment
```
mvn clean install -P autoInstallBundle
```