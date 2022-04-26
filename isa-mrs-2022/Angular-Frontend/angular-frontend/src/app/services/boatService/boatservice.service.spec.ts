import { TestBed } from '@angular/core/testing';

import { BoatserviceService } from './boatservice.service';

describe('BoatserviceService', () => {
  let service: BoatserviceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BoatserviceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
