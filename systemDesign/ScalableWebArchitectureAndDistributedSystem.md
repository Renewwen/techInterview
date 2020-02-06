# Scalable Web Architecture and Distributed Systems

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
