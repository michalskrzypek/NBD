printjson(db.people.aggregate(
    [
        {
            $project: {
                credit: "$credit"}
        },
        {
            $unwind: "$credit"
        },
        {
            $project: {
                balance: { $toDecimal: "$credit.balance" },
                currency: "$credit.currency"
            }
        },
        {
            $group: {
                _id: "$currency",
                totalBalance: {$sum: "$balance"}
            }
        }
    ]
).toArray())