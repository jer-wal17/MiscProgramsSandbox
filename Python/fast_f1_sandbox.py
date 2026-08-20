import fastf1 as ff1

er = ff1.get_events_remaining()
print(er["RoundNumber"], er["Country"],
      er["Location"], er["OfficialEventName"])
