var me =
{
    "sex": "Male",
    "first_name": "Michal",
    "last_name": "Skrzypek",
    "job": "Senior Software Engineer",
    "email": "s23460@pjwstk.edu.pl",
    "location": {
        "city": "Warsaw",
        "address": {
            "streetname": "Chmielna",
            "streetnumber": "39"
        }
    },
    "description": "trolololo id luctus nec molestie sed justo pellentesque viverra pede ac diam cras pellentesque",
    "height": "185.91",
    "weight": "87.03",
    "birth_date": "1997-01-14T08:22:07Z",
    "nationality": "Poland",
    "credit": [
        {
            "type": "jcb",
            "number": "3529195112892551",
            "currency": "PLN",
            "balance": "14265.17"
        }
    ]
}
printjson(db.people.insert(me))