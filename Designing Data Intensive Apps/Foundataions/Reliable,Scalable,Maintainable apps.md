** Reliability ** -> System continues to work correctly (functionality + perf) even in adversity (sf/hw/human error)

1. performs functions expected
2. handles misuse or mistakes from user
3. perf is good enough under expected load / data volume
4. No unauth access or abuse

anticipate faults and cope with them.
Fault tolerant --> prevents faults from causing a failure
Deliberately trigger faults to make system tolerant


Hardware errors -> Hw Redundancy in addition its best to have software fault tolerance  techniques 

Software errors -> Hw failure may not correlate (one node failure may not affect others), but sw faults correlate 
                -> bad input, process affecting shared resources, dependency service slows down, cascading failure
                -> sw faults are usually dormant until triggered by unusual event
                -> careful thinking of scenarios, testing, intentional crash testing, process isolation, monitoring, measuring, checks

Human errors    -> Configuration error by operators
                -> to minimize errors, 
                    use abstractions/UI , 
                    decouple env where users tend to make more mistakes (sandbox), 
                    unit,manual,integration tests
                    quick & easy recovery ( faster rollbacks, gradual new feature rollout, recomputation of data)
                    median(50th) sort all response times then median is the half way point, 95th , 99th, 99.9th percentile
                    
-------------------------------------------------------------------------------------------------------------------

** Scalability **
As system grows (data/traffic volume or complexity) there should be reasonable ways of dealing with growth.
Describing load -> req/sec, ration of read to write in db, active sers in chat room, hit rate on cache
Describing perf -> once load is described. latency vs response time -> waiting time for a req,  
                    Tail latency amplification -> if client req involves multiple backend calls (all run in parallel) & 
                                                  even if one of them is slow it slows the whole response for end user. 
                                                  Even if small portion of backend calls are slow, chance of response being slow is high..
                                                  ..if req requires many backend calls. its called tail latency amp
                    Calculating percentiles -> sort response times and calculate based on it in a rolling window of lets say last 10mins. 
                                                Or can use algorithms such as forward decay, t-digest, HdrHistogram
                                                Averaging percentiles from diff nodes is meaningless, right way to aggregate is to add histograms.
                                                Refer: <a> https://orangematter.solarwinds.com/2016/11/18/why-percentiles-dont-work-the-way-you-think/ </a>
-------------------------------------------------------------------------------------------------------------------

** Maintainability **
people who work on system (ops,eng) should be able to maintain current functionality and adapt system to new usecases.


