import asyncio, os
from binance import AsyncClient, BinanceSocketManager

async def main():
    client = await AsyncClient.create()
    bm = BinanceSocketManager(client)
    sts = bm.kline_socket("BTCUSDT", "1m")
    
    async with sts as sm:
        while True:
            res = await sm.recv()
            os.system("clear")
            print(res)
    
if __name__ == "__main__":
    print("ok")
    loop = asyncio.get_event_loop()
    loop.run_until_complete(main())