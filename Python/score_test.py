team_1_score = 0
team_2_score = 0
MAX_POINTS = 100
points_left = 100
score_diff = 0
leading_team = 0
losing_team = 0


def set_score_diff_and_lead():
    temp_diff = team_1_score - team_2_score
    if temp_diff > 0:
        temp_lead = 1
        temp_lose = 2
    elif temp_diff < 0:
        temp_lead = 2
        temp_lose = 1
        temp_diff *= -1
    else:
        temp_lead = 0
        temp_lose = 0
    return temp_diff, temp_lead, temp_lose


def get_team_to_give_points():
    while True:
        try:
            team_to_give = int(
                input("Pick which team to give 10 points (1 or 2): "))
        except ValueError:
            print("Please enter integer values only")
            continue

        if not (team_to_give == 1 or team_to_give == 2):
            print("Please enter a valid team (1 or 2)")
            continue
        else:
            break
    return team_to_give


while score_diff <= points_left and points_left > 0:
    team_to_give = get_team_to_give_points()
    if team_to_give == 1:
        team_1_score += 10
    elif team_to_give == 2:
        team_2_score += 10
    score_diff, leading_team, losing_team = set_score_diff_and_lead()
    points_left -= 10
    print("Team 1 Score:", team_1_score)
    print("Team 2 Score:", team_2_score)

print
if leading_team != 0:
    print("Team", leading_team, "wins! (Team", losing_team, "cannot catch up)")
else:
    print("Tie game!")
