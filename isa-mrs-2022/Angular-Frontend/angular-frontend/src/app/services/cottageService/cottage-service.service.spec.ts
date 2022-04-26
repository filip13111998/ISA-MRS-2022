import { TestBed } from '@angular/core/testing';

import { CottageServiceService } from './cottage-service.service';

describe('CottageServiceService', () => {
  let service: CottageServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CottageServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
