import { TestBed } from '@angular/core/testing';

import { ParticipationsServiceService } from './participations-service.service';

describe('ParticipationsServiceService', () => {
  let service: ParticipationsServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ParticipationsServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
