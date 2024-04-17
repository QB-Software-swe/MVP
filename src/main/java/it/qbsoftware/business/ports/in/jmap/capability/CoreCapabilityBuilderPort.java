package it.qbsoftware.business.ports.in.jmap.capability;

public interface CoreCapabilityBuilderPort {

    public CoreCapabilityBuilderPort maxSizeUpload(Long maxSizeUpload);

    public CoreCapabilityBuilderPort maxConcurrentUpload(Long maxConcurrentUpload);

    public CoreCapabilityBuilderPort maxCallsInRequest(Long maxCallsInRequest);

    public CoreCapabilityBuilderPort maxObjectInGet(Long maxObjectInGet);

    public CoreCapabilityBuilderPort maxObjectInSet(Long maxObjectInSet);

    public CoreCapabilityBuilderPort collationAlgorithms(String[] algorithms);

    public CoreCapabilityPort build();
}
