
//현재날짜(서력 계산)
//서력(AD-라틴어) = Anno(올해-이번년도) Domini(주-예수 그리스도) = 예수 그리스도의 해
let current = dayjs();
let currentAD = current.format("YYYY년 MM월 DD일");
$("#AD").html("<span'>"+currentAD+"</span>");

//제국력날짜 
//제국력(AI-라틴어) = Anno(올해-이번년도) Imperium(제국, 주권, 통치) = 제국의 해
//계산 식 = 제국력 = 서력 + 3512년 15일 = 1282016일
let currentID = current.add(1282016, "day").format("YYYY년 MM월 DD일");

$("#ID").html("<span'>"+currentID+"</span>");