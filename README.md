# Rolling_Backend

- Service Link : https://rolling.stubee.kr

## Project Structure
```markdown
Rolling-Adapters
  : 외부 api, db 기술을 사용하여 adapter를 구현하는 모듈

Rolling-Api
  : http 요청을 usecase를 호출해 처리하는 모듈

Rolling-Application
  : usecase를 정의 및 구현하는 모듈,
    domainservice를 구현하는 모듈
    외부 api, db 등의 외부 port를 정의한 모듈

Rolling-Batch
  : batch작업을 수행하는 모듈

Rolling-Domains
  : aggregate model, exception, event, domainservice를 정의한 모듈
```

[![Hits](https://hits.seeyoufarm.com/api/count/incr/badge.svg?url=https%3A%2F%2Fgithub.com%2FStuBee2%2FRolling_Backend_V2&count_bg=%238EB6FF&title_bg=%23555555&icon=apachecassandra.svg&icon_color=%23E7E7E7&title=hits&edge_flat=false)](https://hits.seeyoufarm.com)
