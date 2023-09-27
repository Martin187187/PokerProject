import { TestBed } from '@angular/core/testing';

import { GameStatusService } from './game-status.service';

describe('GameStatusService', () => {
  let service: GameStatusService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GameStatusService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
