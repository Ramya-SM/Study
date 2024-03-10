Redis REmote DIctionary Server


Written in C
Data Stored InMemory for faster operations. Can be stored on disk as well
NoSQL advanced key-value data store
Values can be any DS such as strings, hashes, lists, sets, sorted sets, bitmaps, and hyperloglogs.
key is binary-safe String, with a max size of 512 MB, shorter keys better


Redis Data Types

String: 
Memcached can only store string, Redis can store collection of strings too
512MB max
can store any type of data, like text, integers, floats, videos, images, and audio files.
Can be used to store sessionIDs, static HTML, config XML/JSON
If Integers are stored can be used as Counter
List:
LinkedList is used
quick insertion and removal of the element
Read requires scan of whole list
Use when order of insertion & write speed matters but not read. Like storing logs
Set:
Elements not sorted. No duplicates, duh!!! Sets
Takes constant time for adding & removing elements
Use when uniqueness matters. Like No.of unique users visited
Sorted Set:
Each element is assigned a Score. Sorted based on that
If scores tie then Lexicographic order is used & 2 ele cant be same as its set
Hash:
You know hash

Setup for lojers: guide

String SET & GET Commands
SET : set somekey awesome returns OK
GET : get somekey returns awesome
SETEX : setex key x value after x seconds data removed (like ttl)
		Instead can use set key value EX x
PSETEX : for ms lovers. psetex key x value
	Instead can use set key value PX x
SETNX : if key already present then avoid overwriting setnx key value
	Instead can use set key value NX
STRLEN : find length of value for key strlen somekey
MSET :  set multiple once MSET key1 value1 key2 value2
MGET :  MGET key1 key2
Utility Commands
KEYS : get all keys.   keys *
INCR : increments value by 1.  incr key
If value not integer throws error, if key not present creates one with value 1, if try to decrement which doesnt exists the creates with -1.
INCRBY : incrby key x
INCRBYFLOAT : incr by floating point
DECR : 
DECRBY :
DEL : delete one or more keys
FLUSHALL : flushes all keys
APPEND : appends value to key’s value  append key appendVal
List Set/GET Commands
LPUSH : inserts to head of list (like stack)
	  lpush key v1 v2 gets added as v2 v1 
LRANGE : LRANGE key start end lrange key 0 -1 lists all elements
LPOP :  lpop key removes element from head
RPUSH :adds to tail (like queue)
RPOP : removes from tail

List Utility Commands:
LLEN : length of list
LINDEX : find ele at some index LINDEX key index indices starts from 0
LSET : set value at some index LSET key index value
LPUSHX : adds ele to head LPUSHX key value if list doesnt exist then wont do anything
LINSERT : linsert key before/after pivot value inserts value before/after pivot value

Set SET/GET Commands:
SADD : SADD key val1 val2 
SMEMBERS : SADD key to see all members of list
SISMEMBER : SISMEMBER key value member of set, yes 1 or 0 
SCARD : SCARD key number of members in set
SDIFF : SDIFF key1 key2 set difference

Set Modification Commands:
SUNION : SREM key1 key2 key3 … returns union
SREM : SREM key value1 value2 … removes those values 
SPOP : SPOP key count removes random values from set 
SMOVE : SMOVE source dest member

Sorted Set SET/GET Commands:
ZADD : ZADD key score value …
ZRANGE : ZRANGE key start end
ZRANGEBYSCORE : ZRANGEBYSCORE key start end fetches elements  in the range of scores 
ZCARD : ZCARD key count of elem in sorted set
ZCOUNT : ZCOUNT key min max count of elem in range

Sorted Set Modification Commands:
ZREM : ZREM key value removes values
ZRANK : ZRANK key member finds index, 0 => lowest score member
ZREVRANK : ZREVRANK key member 0 => highest score member
ZSCORE : ZSCORE key member  finds score value of member

Hash Set/GET Commands:
HMSET : HMSET key field1 value1 field2 value2
HGETALL : hgetall key 
HGET : HGET key field
HEXISTS : HEXISTS key field
HDEL : hdel key field
HSETNX : HSETNX key field value

Redis as Queue: Redis Publish Subscriber Model

SUBSCRIBE channel
PUBLISH channel message
UNSUBSCRIBE channel
PSUBSCRIBE prefix* subscribe to all channels starting with prefix
PUNSUBSCRIBE prefix*


Redis Security:
Supports plain-text password. Does NOT support different access levels.
The password should be set in the config file using requirepass config
config get requirepass
config set requirepass password
Try to execute cmds without authenticating first then returns NOAUTH error
AUTH password

Access Restriction:
rename the commands which only the ones who know can execute.
Steps to rename a command:
Add the following command to the redis.conf file. 
rename-command flushall purgeall
Run the redis-server.exe, followed by the filename of your config file.
redis-server.exe redis.conf
Now the flushall command will not be available

Transactions In Redis: 
All transactions are atomic 
Cannot rollback transactions like RDBs
blocks the execution of cmds from other clients while cmds from one client are running.
Lets say one syntactically correct cmd fails then it executes other commands
If cmd is syntactically incorrect then whole transaction fails

Optimistic locking in Redis :
If a client is creating transactions but meanwhile another client updates the key before our client executes the transaction then this could be a problem.
To avoid this our client can watch a key & if its modified transaction will abort.
Example:
> watch k1
> multi
> set k1 10
> exec
(nil)

Commands
MULTI : command tells redis that transaction is starting
EXEC : When the transaction is started redis client queues all commands & when exec is executed client sends these to server & server executes  in same order
DISCARD : discards & wont send to server
WATCH 
UNWATCH


Redis Persistence 

Redis is an in memory(RAM) datastore so if server crashes data will be lost. To prevent that there are 2 ways

RDB (Redis DB) Snapshotting

 Data will be stored in disk as a dump file after some interval. 
Default config is as below in redis.conf:
save 900 1  # after 900s(15min) if 1 key changed
save 300 10 # after 300s (5min) if 10 key changed
save 60 10000  # after 60s (1min) if 10000 key changed

save command can be used to manually save data to disk but should avoid this because this blocks the redis server. 
bgsave can be used instead, this creates a child process to snapshot

To disable snapshotting delete all save directives in redis.conf & restart the server.


RDB Configurations

1) rdbcompression: Default value is true
    If enabled REdis uses LZF compression. To save CPU cycles in child process can make it false.
2) rdbchecksum: 
    If enabled then calculates CRC64 checksum added to end of file.
    Makes resistant to corruption but performance degrades by 10% while saving/loading RDB files.
    If disabled server stores 0 as checksum which tells the server to skip checksum calculation.
3) stop-writes-on-bgsave-errors: default is true
    If latest bgsave failed then server will stop accepting writes & automatically resumes once bgsave starts working. Advised not to change to false as to alert user on the issue.
4) dbfilename: default is dump.rdb 
  

	This method has its downfalls because Redis creates a child process for snapshotting & if data is updates while the child is writing then the child has to make a copy of that too. If data is large then it may stop clients for a few ms to seconds. 




AOF persistence (Append only file persistence)

Every write received by the Redis server is logged into a file. When Redis restarts all commands in the file will be run again & entire data set is recreated.

After some time it gets huge so Redis once in a while rewrites the file & keeps only latest value for the key.

The rewrite frequency can be reconfigured based on current size of the file or the allowed % increase since the size of the latest rewrite.

When Redis sends commands to be appended to the file, the OS stores these in buffers & flushes every x second, hence there is a chance of losing the data.Redis forces the OS buffer flush every second. Can make OS buffer flush for every command but that would affect redis command response time.

AOF Config:

appendonly: default is false. To enable/disable AOF
appendfilename: default is appendonly.aof
appendfsync: OS has fsync() call that tells it to flush the buffer every x seconds. Redis can control that using this directive. 3 values: 1. No: OS decides 2.always: on every write 3.everysec: every second (default)
no-appendfsync-on-rewrite: if set to true, 
auto-aof-rewrite-percentage: executes BGREWRITEAOF when AOF grows by %. Default is 100%.
auto-aof-rewrite-min-size: default is 64MB. Minimum size the AOF has to be reached to rewrite. This overrides % config.


Conclusion: Redis uses snapshotting as default strategy. If okay with losing few seconds of data then this should be used. If cant lose data then use AOF. Can use both but Redis will use AOF to populate data because of accuracy.



Redis Replication





