<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<project xmlns="http://grogra.de/registry" graph="graph.xml">
 <import plugin="de.grogra.imp" version="2.2.1"/>
 <import plugin="de.grogra.math" version="2.2.1"/>
 <import plugin="de.grogra.pf" version="2.2.1"/>
 <import plugin="de.grogra.imp3d" version="2.2.1"/>
 <import plugin="de.grogra.rgg" version="2.2.1"/>
 <registry>
  <ref name="project">
   <ref name="objects">
    <ref name="files">
     <de.grogra.pf.ui.registry.SourceFile mimeType="text/x-grogra-rgg" name="Forest.rgg" systemId="pfs:Forest.rgg"/>
     <de.grogra.pf.ui.registry.SourceFile mimeType="text/x-java" name="P.java" systemId="pfs:P.java"/>
     <de.grogra.pf.ui.registry.SourceFile mimeType="text/x-java" name="PG.java" systemId="pfs:PG.java"/>
     <de.grogra.pf.ui.registry.SourceFile mimeType="text/x-java" name="PP.java" systemId="pfs:PP.java"/>
     <de.grogra.pf.ui.registry.SourceFile mimeType="text/x-java" name="STrunkBase.java" systemId="pfs:STrunkBase.java"/>
     <de.grogra.pf.ui.registry.SourceFile mimeType="text/x-java" name="SRootNode.java" systemId="pfs:SRootNode.java"/>
     <de.grogra.pf.ui.registry.SourceFile mimeType="text/x-java" name="SRootBase.java" systemId="pfs:SRootBase.java"/>
     <de.grogra.pf.ui.registry.SourceFile mimeType="text/x-java" name="SRoot.java" systemId="pfs:SRoot.java"/>
     <de.grogra.pf.ui.registry.SourceFile mimeType="text/x-java" name="SInternode.java" systemId="pfs:SInternode.java"/>
     <de.grogra.pf.ui.registry.SourceFile mimeType="text/x-java" name="SBud.java" systemId="pfs:SBud.java"/>
     <de.grogra.pf.ui.registry.SourceFile mimeType="text/x-java" name="S.java" systemId="pfs:S.java"/>
     <de.grogra.pf.ui.registry.SourceFile mimeType="text/x-java" name="SBranch.java" systemId="pfs:SBranch.java"/>
     <de.grogra.pf.ui.registry.SourceFile mimeType="text/x-java" name="Util.java" systemId="pfs:Util.java"/>
     <de.grogra.pf.ui.registry.SourceFile mimeType="text/x-java" name="VoxBase.java" systemId="pfs:VoxBase.java"/>
     <de.grogra.pf.ui.registry.SourceFile mimeType="text/x-java" name="Species.java" systemId="pfs:Species.java"/>
     <de.grogra.pf.ui.registry.SourceFile mimeType="text/x-java" name="SpeciesPool.java" systemId="pfs:SpeciesPool.java"/>
     <de.grogra.pf.ui.registry.SourceFile mimeType="text/x-java" name="MGer.java" systemId="pfs:MGer.java"/>
     <de.grogra.pf.ui.registry.SourceFile mimeType="text/x-java" name="MGro.java" systemId="pfs:MGro.java"/>
     <de.grogra.pf.ui.registry.SourceFile mimeType="text/x-java" name="MLight.java" systemId="pfs:MLight.java"/>
     <de.grogra.pf.ui.registry.SourceFile mimeType="text/x-java" name="MLightComputer.java" systemId="pfs:MLightComputer.java"/>
     <de.grogra.pf.ui.registry.SourceFile mimeType="text/x-java" name="MLightCopier.java" systemId="pfs:MLightCopier.java"/>
     <de.grogra.pf.ui.registry.SourceFile mimeType="text/x-java" name="MLightLateral.java" systemId="pfs:MLightLateral.java"/>
     <de.grogra.pf.ui.registry.SourceFile mimeType="text/x-java" name="MLightNotifier.java" systemId="pfs:MLightNotifier.java"/>
     <de.grogra.pf.ui.registry.SourceFile mimeType="text/x-java" name="MMor.java" systemId="pfs:MMor.java"/>
     <de.grogra.pf.ui.registry.SourceFile mimeType="text/x-java" name="Report.java" systemId="pfs:Report.java"/>
    </ref>
    <ref name="images">
     <de.grogra.pf.ui.registry.FileObjectItem mimeType="image/png" name="Leaf1_small" objDescribes="true" systemId="pfs:images/Leaf1_small.png" type="de.grogra.imp.objects.FixedImageAdapter"/>
     <de.grogra.pf.ui.registry.FileObjectItem mimeType="image/png" name="Leaf2_small" objDescribes="true" systemId="pfs:images/Leaf2_small.png" type="de.grogra.imp.objects.FixedImageAdapter"/>
     <de.grogra.pf.ui.registry.FileObjectItem mimeType="image/png" name="Leaf3_small" objDescribes="true" systemId="pfs:images/Leaf3_small.png" type="de.grogra.imp.objects.FixedImageAdapter"/>
     <de.grogra.pf.ui.registry.FileObjectItem mimeType="image/jpeg" name="Trunk1" objDescribes="true" systemId="pfs:images/Trunk1.jpg" type="de.grogra.imp.objects.FixedImageAdapter"/>
     <de.grogra.pf.ui.registry.FileObjectItem mimeType="image/jpeg" name="bark2" objDescribes="true" systemId="pfs:images/bark2.jpg" type="de.grogra.imp.objects.FixedImageAdapter"/>
    </ref>
    <ref name="meta">
     <de.grogra.pf.registry.NodeReference name="Forest" ref="842343311"/>
    </ref>
    <ref name="3d">
     <ref name="shaders">
      <de.grogra.pf.registry.SONodeReference name="leaf1" objDescribes="true" ref="805301470"/>
      <de.grogra.pf.registry.SONodeReference name="leaf2" objDescribes="true" ref="805301472"/>
      <de.grogra.pf.registry.SONodeReference name="leaf3" objDescribes="true" ref="805301474"/>
      <de.grogra.pf.registry.SONodeReference name="bark1" objDescribes="true" ref="805301476"/>
      <de.grogra.pf.registry.SONodeReference name="bark2" objDescribes="true" ref="805301478"/>
     </ref>
    </ref>
   </ref>
  </ref>
  <ref name="workbench">
   <ref name="state">
    <de.grogra.pf.ui.registry.Layout name="layout">
     <de.grogra.pf.ui.registry.MainWindow>
      <de.grogra.pf.ui.registry.Split location="0.54900664">
       <de.grogra.pf.ui.registry.Split location="0.8430851" orientation="0">
        <de.grogra.pf.ui.registry.Split orientation="0">
         <de.grogra.pf.registry.Link source="/ui/panels/rgg/toolbar"/>
         <de.grogra.pf.ui.registry.PanelFactory source="/ui/panels/3d/defaultview">
          <de.grogra.pf.registry.Option name="panelId" type="java.lang.String" value="/ui/panels/3d/defaultview"/>
          <de.grogra.pf.registry.Option name="panelTitle" type="java.lang.String" value="View"/>
          <de.grogra.pf.registry.Option name="view" type="de.grogra.imp3d.View3D" value="graphDescriptor=[de.grogra.imp.ProjectGraphDescriptor]visibleScales={false false false true false}visibleLayers={true true true true true true true true true true true true true true true true}epsilon=1.0E-6 visualEpsilon=0.01 magnitude=1.0 camera=(minZ=0.1 maxZ=2000.0 projection=[de.grogra.imp3d.PerspectiveProjection aspect=1.0 fieldOfView=1.0471976]transformation=(0.33137876605674405 -0.9434978078408587 0.0 20.384917603137403 -0.2923523503502971 -0.10268106646121329 0.9507821526635497 -23.408960495450106 -0.8970608767727353 -0.31506901653864317 -0.3098601267752447 -59.24602793011689 0.0 0.0 0.0 1.0))eventFactory=[de.grogra.imp3d.DefaultView3DEventFactory]"/>
         </de.grogra.pf.ui.registry.PanelFactory>
        </de.grogra.pf.ui.registry.Split>
        <de.grogra.pf.ui.registry.Split orientation="0">
         <de.grogra.pf.ui.registry.Tab selectedIndex="0">
          <de.grogra.pf.registry.Link source="/ui/panels/fileexplorer"/>
          <de.grogra.pf.registry.Link source="/ui/panels/objects/meta"/>
         </de.grogra.pf.ui.registry.Tab>
         <de.grogra.pf.registry.Link source="/ui/panels/statusbar"/>
        </de.grogra.pf.ui.registry.Split>
       </de.grogra.pf.ui.registry.Split>
       <de.grogra.pf.ui.registry.Split location="0.7606383" orientation="0">
        <de.grogra.pf.ui.registry.Tab selectedIndex="0">
         <de.grogra.pf.ui.registry.PanelFactory source="/ui/panels/texteditor">
          <de.grogra.pf.registry.Option name="documents" type="java.lang.String" value="&quot;\&quot;pfs:Forest.rgg\&quot;,\&quot;pfs:MGer.java\&quot;,\&quot;pfs:MGro.java\&quot;,\&quot;pfs:MLight.java\&quot;,\&quot;pfs:MLightComputer.java\&quot;,\&quot;pfs:MLightCopier.java\&quot;,\&quot;pfs:MLightLateral.java\&quot;,\&quot;pfs:MLightNotifier.java\&quot;,\&quot;pfs:MMor.java\&quot;,\&quot;pfs:P.java\&quot;,\&quot;pfs:PG.java\&quot;,\&quot;pfs:PP.java\&quot;,\&quot;pfs:Report.java\&quot;,\&quot;pfs:S.java\&quot;,\&quot;pfs:SBranch.java\&quot;,\&quot;pfs:SBud.java\&quot;,\&quot;pfs:SInternode.java\&quot;,\&quot;pfs:Species.java\&quot;,\&quot;pfs:SpeciesPool.java\&quot;,\&quot;pfs:SRoot.java\&quot;,\&quot;pfs:SRootBase.java\&quot;,\&quot;pfs:SRootNode.java\&quot;,\&quot;pfs:STrunkBase.java\&quot;,\&quot;pfs:Util.java\&quot;,\&quot;pfs:VoxBase.java\&quot;&quot;"/>
          <de.grogra.pf.registry.Option name="panelId" type="java.lang.String" value="/ui/panels/texteditor"/>
          <de.grogra.pf.registry.Option name="panelTitle" type="java.lang.String" value="jEdit - Forest.rgg"/>
          <de.grogra.pf.registry.Option name="selected" type="java.lang.String" value="pfs:Forest.rgg"/>
         </de.grogra.pf.ui.registry.PanelFactory>
         <de.grogra.pf.registry.Link source="/ui/panels/attributeeditor"/>
        </de.grogra.pf.ui.registry.Tab>
        <de.grogra.pf.ui.registry.Tab selectedIndex="0">
         <de.grogra.pf.registry.Link source="/ui/panels/log"/>
         <de.grogra.pf.registry.Link source="/ui/panels/rgg/console"/>
        </de.grogra.pf.ui.registry.Tab>
       </de.grogra.pf.ui.registry.Split>
      </de.grogra.pf.ui.registry.Split>
     </de.grogra.pf.ui.registry.MainWindow>
    </de.grogra.pf.ui.registry.Layout>
   </ref>
  </ref>
 </registry>
</project>
