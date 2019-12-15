import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HomepageTextComponent } from './homepage-text.component';

describe('HomepageTextComponent', () => {
  let component: HomepageTextComponent;
  let fixture: ComponentFixture<HomepageTextComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HomepageTextComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HomepageTextComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
