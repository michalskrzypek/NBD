printjson(db.people.find({ birth_date: { $gte: "1970-11-29T08:53:40Z" } }, { first_name: 1, last_name: 1, "location.city": 1 }).toArray())
