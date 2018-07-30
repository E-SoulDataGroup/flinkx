# Http读取插件（httpreader）

## 1. 配置样例

```
{
    "job": {
        "setting": {
            "speed": {
                "channel": 1,
                "bytes": 10000
            },
            "errorLimit": {
                "record": 0,
                "percentage": 0
            }
        },
        "content": [
            {
                "reader": {
                    "name": "httpreader",
                    "parameter": {
                        "httpUrl": "http://xxxxx/Log/tab_map_race/tab_map_race_20180725_10.log",
                        "column": [
                            {
                                "index": 0
                            },
                            {
                                "index": 1
                            },
                            {
                                "index": 2
                            },
                            {
                                "index": 3
                            },
							{
                                "index": 4
                            },
                            {
                                "index": 5
                            },
                            {
                                "index": 6
                            },
                            {
                                "index": 7
                            },
							{
                                "index": 8
                            },
                            {
                                "index": 9
                            },
                            {
                                "index": 10
                            },
                            {
                                "index": 11
                            },
							{
                                "index": 12
                            },
                            {
                                "index": 13
                            },
                            {
                                "index": 14
                            },
                            {
                                "index": 15
                            },
							{
                                "index": 16
                            },
                            {
                                "index": 17
                            },
                            {
                                "index": 18
                            },
                            {
                                "index": 19
                            },
							{
                                "index": 20
                            },
                            {
                                "index": 21
                            },
                            {
                                "index": 22
                            },
                            {
                                "index": 23
                            },
							{
                                "index": 24
                            },
                            {
                                "index": 25
                            },
                            {
                                "index": 26
                            },
                            {
                                "index": 27
                            },
							{
                                "index": 28
                            },
                            {
                                "index": 29
                            },
                            {
                                "index": 30
                            },
                            {
                                "index": 31
                            },
							{
                                "index": 32
                            },
                            {
                                "index": 33
                            },
                            {
                                "index": 34
                            },
                            {
                                "index": 35
                            },
							{
                                "index": 36
                            },
                            {
                                "index": 37
                            },
                            {
                                "index": 38
                            },
                            {
                                "index": 39
                            },
							{
                                "index": 40
                            },
                            {
                                "index": 41
                            },
                            {
                                "index": 42
                            },
                            {
                                "index": 43
                            },
							{
                                "index": 44
                            },
                            {
                                "index": 45
                            },
                            {
                                "index": 46
                            },
                            {
                                "index": 47
                            },
							{
                                "index": 48
                            },
                            {
                                "index": 49
                            },
                            {
                                "index": 50
                            },
                            {
                                "index": 51
                            },
							{
                                "index": 52
                            },
                            {
                                "index": 53
                            },
                            {
                                "index": 54
                            }
                        ],
                        "encoding": "UTF-8",
                        "fieldDelimiter": ","
                    }
                },
                "writer": {
                    "name": "hdfswriter",
                    "parameter": {
                        "hadoopConfig": {
                            "dfs.nameservices": "DHTestCluster",
                            "dfs.ha.namenodes.DHTestCluster": "nn1,nn2",
                            "dfs.namenode.rpc-address.DHTestCluster.nn1": "hadoop1.test.yunwei.puppet.dh:8020",
                            "dfs.namenode.rpc-address.DHTestCluster.nn2": "hadoop2.test.yunwei.puppet.dh:8020",
                            "dfs.client.failover.proxy.provider.DHTestCluster": "org.apache.hadoop.hdfs.server.namenode.ha.ConfiguredFailoverProxyProvider"
                        },
                        "defaultFS": "hdfs://DHTestCluster",
                        "fileType": "ORC",
                        "fileName": "par_dt=2018-07-25",
                        "column": [
                            {
                                "name": "iuin",
                                "type": "BIGINT"
                            },
                            {
                                "name": "map_id",
                                "type": "STRING"
                            },
							{
                                "name": "win",
                                "type": "BIGINT"
                            },
                            {
                                "name": "time",
                                "type": "BIGINT"
                            },
							{
                                "name": "record_time",
                                "type": "STRING"
                            },
                            {
                                "name": "auto_match_race",
                                "type": "BIGINT"
                            },
							{
                                "name": "prize",
                                "type": "BIGINT"
                            },
                            {
                                "name": "race_id",
                                "type": "STRING"
                            },
							{
                                "name": "hero_type_id",
                                "type": "STRING"
                            },
                            {
                                "name": "kill_hero_nums",
                                "type": "BIGINT"
                            },
							{
                                "name": "death_nums",
                                "type": "BIGINT"
                            },
                            {
                                "name": "assistance_nums",
                                "type": "BIGINT"
                            },
							{
                                "name": "money",
                                "type": "BIGINT"
                            },
                            {
                                "name": "race_model",
                                "type": "BIGINT"
                            },
							{
                                "name": "equip_score",
                                "type": "BIGINT"
                            },
                            {
                                "name": "fight_value",
                                "type": "BIGINT"
                            },
							{
                                "name": "hero_value",
                                "type": "BIGINT"
                            },
                            {
                                "name": "trial_shero",
                                "type": "BIGINT"
                            },
							{
                                "name": "seat_index",
                                "type": "BIGINT"
                            },
                            {
                                "name": "apm",
                                "type": "BIGINT"
                            },
							{
                                "name": "best_hero",
                                "type": "BIGINT"
                            },
                            {
                                "name": "gudan",
                                "type": "BIGINT"
                            },
							{
                                "name": "best_kill",
                                "type": "BIGINT"
                            },
                            {
                                "name": "best_assist",
                                "type": "BIGINT"
                            },
							{
                                "name": "best_money",
                                "type": "BIGINT"
                            },
                            {
                                "name": "best_build",
                                "type": "BIGINT"
                            },
							{
                                "name": "fange",
                                "type": "BIGINT"
                            },
                            {
                                "name": "brush",
                                "type": "BIGINT"
                            },
							{
                                "name": "map_fight_score",
                                "type": "BIGINT"
                            },
                            {
                                "name": "player_score",
                                "type": "BIGINT"
                            },
							{
                                "name": "max_combo_kill",
                                "type": "BIGINT"
                            },
                            {
                                "name": "kill_build",
                                "type": "BIGINT"
                            },
							{
                                "name": "cmp",
                                "type": "BIGINT"
                            },
                            {
                                "name": "hero_level",
                                "type": "BIGINT"
                            },
							{
                                "name": "kill_soildiers",
                                "type": "BIGINT"
                            },
                            {
                                "name": "im_kill_soildiers",
                                "type": "BIGINT"
                            },
							{
                                "name": "ser_mode_result",
                                "type": "BIGINT"
                            },
                            {
                                "name": "sum_best_assist",
                                "type": "BIGINT"
                            },
							{
                                "name": "sum_best_build",
                                "type": "BIGINT"
                            },
                            {
                                "name": "sum_best_hero",
                                "type": "BIGINT"
                            },
							{
                                "name": "sum_best_kill",
                                "type": "BIGINT"
                            },
                            {
                                "name": "sum_best_money",
                                "type": "BIGINT"
                            },
							{
                                "name": "sum_fange",
                                "type": "BIGINT"
                            },
                            {
                                "name": "sum_gudan",
                                "type": "BIGINT"
                            },
							{
                                "name": "char_name",
                                "type": "STRING"
                            },
                            {
                                "name": "race_end_flag",
                                "type": "BIGINT"
                            },
							{
                                "name": "fight_score",
                                "type": "BIGINT"
                            },
                            {
                                "name": "new_client",
                                "type": "BIGINT"
                            },
							{
                                "name": "match_race_type",
                                "type": "BIGINT"
                            },
                            {
                                "name": "jungong",
                                "type": "BIGINT"
                            },
							{
                                "name": "online",
                                "type": "BIGINT"
                            },
                            {
                                "name": "area_info",
                                "type": "STRING"
                            },
							{
                                "name": "zhanchang_hero_score",
                                "type": "BIGINT"
                            },
                            {
                                "name": "town_num",
                                "type": "BIGINT"
                            },
							{
                                "name": "race_ip",
                                "type": "STRING"
                            }
                        ],
                        "path": "/user/hive/warehouse/m3gcn_data_log.db/tab_map_race",
                        "writeMode": "append",
                        "fieldDelimiter": "\\001"
                    }
                }
            }
        ]
    }
}

```

## 2.参数说明

* **httpUrl**

	* 描述：http地址。<br />

	* 必选：是 <br />

	* 默认值：无 <br />
	
* **column**

	* 描述：读取字段列表，type指定源数据的类型，index指定当前列来自于文本第几列(以0开始)，value指定当前类型为常量。
	

		用户可以指定column字段信息，配置如下：

		```json
		{
           "index": 0    //从远程FTP文件文本第一列获取int字段
        },
        {
           "type": "string",
           "value": "hello"  //从HttpReader内部生成hello的字符串字段作为当前字段
        }
		```

		对于用户指定Column信息，type必须填写，index/value必须选择其一。

	* 必选：是 <br />

	* 默认值：全部按照string类型读取 <br />

* **fieldDelimiter**

	* 描述：读取的字段分隔符 <br />

	* 必选：是 <br />

	* 默认值：, <br />

* **encoding**

	* 描述：读取文件的编码配置。<br />

 	* 必选：否 <br />

 	* 默认值：utf-8 <br />