package com.damuzee.db;

import com.damuzee.model.Member;

public abstract class AbstractMemberAccess extends DataAccessAdapter<Member> {
    public abstract Member getSuperiorMember(Member Member);
}
