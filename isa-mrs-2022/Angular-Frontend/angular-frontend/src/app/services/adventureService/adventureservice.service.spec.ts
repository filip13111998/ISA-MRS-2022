import { TestBed } from '@angular/core/testing';

import { AdventureserviceService } from './adventureservice.service';

describe('AdventureserviceService', () => {
  let service: AdventureserviceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AdventureserviceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
