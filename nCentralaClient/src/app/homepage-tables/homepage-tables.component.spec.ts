import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HomepageTablesComponent } from './homepage-tables.component';

describe('HomepageTablesComponent', () => {
  let component: HomepageTablesComponent;
  let fixture: ComponentFixture<HomepageTablesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HomepageTablesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HomepageTablesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
