db.people.aggregate([{
    $group: {_id: null, jobs: {$addToSet: "$job"}}
}])

//or
//printjson(db.people.distinct("job"))