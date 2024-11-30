import com.rajesh.code.action.Awsaction

Map awsMap = [
        aws: [
                account1: [
                        region : 'eu-west-1',
                        credsID: 'account-1-creds'
                ],
                account2: [
                        region : 'eu-west-1',
                        credsID: 'aws-own-creds'
                ]
        ]
]
Map res = [:]
awsMap["aws"].each {k,v ->
    Awsaction sam = new Awsaction(v["credsID"],v["region"])
    res.put(k,sam)
}

println(res)