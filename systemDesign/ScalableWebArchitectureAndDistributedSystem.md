# [Scalable Web Architecture and Distributed Systems](https://www.aosabook.org/en/distsys.html)

## 1.1. Principles of Web Distributed Systems Design

Key principles that influence the design of large-scale web systems
- **Avaiability:** 
    - The `uptime` of a website is absolutely critical!
    - `constantly available` and `resilient to failure`
    - Careful consideration of `redundancy` for key components 
    - rapid recovery in the event of partial system failures.
    - graceful degradation when problems occur.
- **Performance:**
    - the `speed` of a website affects usage and user satisfaction
    - optimize for `fast responses` and `low latency`
    - Example: Search engine rankings
- **Reliability:**
    - a request for data will `consistently return the same data`
    - in the event of `data changes` or `is updated`
    - persist and can be relied on to be in place for `future retrieval`
- **Scalability:**
    - `size` is just one aspect of scale
    - Scalability can refer to many `different parameters` of the system: 
    - how much additional traffic can it handle, 
    - how easy is it to add more storage capacity, 
    - how many more transactions can be processed.
- **Manageability**
    - Designing a system that is `easy to operate`
    - `maintenance` and `updates`.
    - easy of `diagnosing` and `understanding problems` when they occur
    - easy of making `updates or modifications`
    - how simple the system is to operate
- **Cost**
    - `hardware` and `software` costs
    - The amount of `developer time` the system takes to build, 
    - The amount of `operational effort` required to run the system 
    - The amount of `training required` should all be considered. 
    - Cost is the total cost of `ownership`.

## 1.2 The Basics

**Core factors:** services, redundancy, partitions, handling failure

### Example: Image Hosting Application

For big sites that host and deliver lots of images, there are challenges in building an architecture that is **cost-effective, highly available, and has low latency** (fast retrieval).

Let's assume that this application has two key parts:
- The ability to upload (write) an image to the server
- The ability to query for an image.

Important aspects of the system:
- Storage Scalability: no limit to the numebr of images
- low latency for image downloads/requests.
- If a user uploads an image, the image should always be there (data reliability)
- The system should be easy to maintain
- cost-effective

**Simplified diagram of the functionality**
![](https://www.aosabook.org/images/distsys/imageHosting1.jpg)

### Services

In our example, all requests to upload and retrieve images are processed by the same server; however, as the system needs to scale it makes sense to break out these two functions into their own services.

> Even if the upload and download speeds are the same, read files will typically be read from cache, and writes will have to go to disk eventually. Even if everything is in memory or read from disks (like SSDs), database writes will almost always be slower than reads. 

**Splitting out reads and writes**
![](https://www.aosabook.org/images/distsys/imageHosting2.png)

This allows us to scale each of them independently (since it is likely we will always do more reading than writing)

>Troubleshoot: slow reads  
**Two defferent endpoints**

Both of these services still leverage the global corpus of images, but they are free to optimize their own performance with service-appropriate methods

### Redundancy
**single points of failure**

Create multiple, or redundant, copies handle the failure of services and data.

> If there is a core piece of functionality for an application, ensuring that multiple copies or versions are running simultaneously can secure against the failure of a single node.

In our image server application, all images would have redundant copies on another piece of hardware somewhere:

**Image hosting application with redundancy**
![](https://www.aosabook.org/images/distsys/imageHosting3.png)

### Partitions
Two choices:
- Scale vertically: adding more resources to an individual server.
- Scale horizontally: add more nodes

**Image hosting application with redundancy and partitioning**
![](https://www.aosabook.org/images/distsys/imageHosting4.png)

## 1.3 The Building Blocks of Fast and Scalable Data Access

There are many options that you can employ to make this easier:
- Caches
- Proxies
- Indexes
- Load balances

### Caches
>Recently requested data is likely to be requested again.  

They are used in almost every layer of computing:  
- hardware,  
- operating systems,  
- web browsers,  
- web applications and more. 

>Caches can exist at all levels in architecture, but are often found at the level nearest to the front end, where they are implemented to return data quickly without taxing downstream levels.

**Multiple caches**
![](https://www.aosabook.org/images/distsys/multipleCaches.png)

However, if your load balancer randomly distributes requests across the nodes, the same request will go to different nodes, thus increasing cache misses. Two choices for overcoming this hurdle are global caches and distributed caches.

### Global Cache

There are two common forms of global caches depicted in the diagrams.  
**Global cache where cache is responsible for retrieval**
![](https://www.aosabook.org/images/distsys/globalCache1.png)

**Global cache where request nodes are responsible for retrieval**
![](https://www.aosabook.org/images/distsys/globalCache2.png)

### Distributed Cache
![](https://www.aosabook.org/images/distsys/distributedCaching.png)

## Proxies
Imagine there is a request for the same data (let's call it littleB) across several nodes, and that piece of data is not in the cache. If that request is routed thought the proxy, then all of those requests can be collapsed into one, which means we only have to read littleB off disk once.

![](https://www.aosabook.org/images/distsys/collapseRequests.png)

There is some cost associated with this design:  
dis:
- since each request can have slightly higher latency
- some requests may be slightly delayed to be grouped with similar ones

adv:
- improve performance in high load situations, 
- particularly when that same data is requested over and over. 

Another great way to use the proxy is to not just collapse requests for the same data, but also to collapse requests for data that is spatially close together in the origin store (consecutively on disk).

![](https://www.aosabook.org/images/distsys/collapseRequestsSpatial.png)

This can make a really big difference in request time when you are randomly accessing across TBs of data!  
Proxies are especially helpful under high load situations, or when you have limited caching, since they can essentially batch several requests into one.

>It is worth noting that you can use proxies and caches together, but generally it is best to put the cache in front of the proxy, for the same reason that it is best to let the faster runners start first in a crowded marathon race. 


## Indexes
Using an index to access your data quickly is a well-known strategy for optimizing data access performance; probably the most well known when it comes to databases. 

**Trade-offs:**  
An index makes the trade-offs of increased storage overhead and slower writes (since you must both write the data and update the index) for the benefit of faster reads.

![](https://www.aosabook.org/images/distsys/indexes.jpg)
An index can be used like a table of contents that directs you to the location where your data lives.

## Load Balancers

Load balancers are a principal part of any architecture, as their role is to distribute load across a set of nodes responsible for servicing requests. This allows multiple nodes to transparently service the same function in a system. 

heir main purpose is to handle a lot of simultaneous connections and route those connections to one of the request nodes, allowing the system to scale to service more requests by just adding nodes.

![](https://www.aosabook.org/images/distsys/loadBalancer.png)

There are many different algorithms that can be used to service requests, including picking a random node, round robin, or even selecting the node based on certain criteria, such as memory or CPU utilization.

In a distributed system, load balancers are often found at the very front of the system, such that all incoming requests are routed accordingly. In a complex distributed system, it is not uncommon for a request to be routed to multiple load balancers

![](https://www.aosabook.org/images/distsys/multipleLoadBalancers.png)

## Queues

![](https://www.aosabook.org/images/distsys/synchronousRequest.png)

![](https://www.aosabook.org/images/distsys/queues.png)
